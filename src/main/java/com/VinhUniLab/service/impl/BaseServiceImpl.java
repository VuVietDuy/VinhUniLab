package com.VinhUniLab.service.impl;


import com.VinhUniLab.entity.BaseEntity;
import com.VinhUniLab.model.request.SearchReq;
import com.VinhUniLab.query.CustomRsqlVisitor;
import com.VinhUniLab.repository.BaseRepository;
import com.VinhUniLab.service.BaseAttributesService;
import com.VinhUniLab.service.BaseService;
import com.VinhUniLab.utils.ObjectMapperUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.*;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;


import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public abstract class BaseServiceImpl<E extends BaseEntity, R extends BaseRepository<E>>
        extends BaseAttributesService<R, E, Long>
        implements BaseService<E> {
    protected static final String DELETED_FILTER = ";status!=-1";

    @Override
    @Transactional(rollbackFor = Exception.class)
    public E create(E t) {
        return this.repository.save(t);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public E update(Long id, E t) {
        E entity = getById(id);
        ObjectMapperUtils.map(t, entity);
        return this.repository.save(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        this.repository.deleteById(id);
    }



    @Override
    public E getById(Long id) throws RuntimeException {
        return this.repository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public List<E> getAll() {
        return this.repository.findAll();
    }

    @Override
    public List<E> getByIds(List<Long> ids) {
        return this.repository.findAllById(ids);
    }

    @Override
    public List<E> save(List<E> es) {
        return this.repository.saveAll(es);
    }

    @Override
    public Page<E> search(SearchReq req) {
        Set<String> ignoredFields = getIgnoredFields(getEntityClass(), null, new HashSet<>());
        Node rootNode = new RSQLParser().parse(req.getFilter());
        // Remove ignore fileds in filter
        if (!ignoredFields.isEmpty())
            rootNode = filterIgnoredFields(rootNode, ignoredFields);
        Specification<E> spec = rootNode.accept(new CustomRsqlVisitor<E>());
        String[] sortList = req.getSort().split(",");
        Sort.Direction direction = sortList[1].equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(req.getPage(), req.getSize(), direction, sortList[0]);
        return this.repository
                .findAll(spec, pageable);
    }

    private Set<String> getIgnoredFields(Class<?> clazz, String parentPath, Set<String> visitedPaths) {
        Set<String> ignored = new HashSet<>();

        String currentPath = (parentPath == null ? clazz.getSimpleName() : parentPath);

        // Nếu path này đã duyệt → dừng để tránh vòng lặp vô hạn cha -> con -> cha
        String[] paths = currentPath.split("\\.");
        if (paths.length >= 2 && paths[paths.length - 2].equals(paths[paths.length - 1]))
            return ignored;
        visitedPaths.add(currentPath);

        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(JsonIgnore.class)) {
                String fieldPath = (parentPath == null ? field.getName() : parentPath + "." + field.getName());
                ignored.add(fieldPath);
            } else {
                // Nếu là quan hệ entity thì duyệt đệ quy
                if (field.isAnnotationPresent(ManyToOne.class) || field.isAnnotationPresent(OneToOne.class)) {
                    String prefix = (parentPath == null ? field.getName() : parentPath + "." + field.getName());
                    ignored.addAll(getIgnoredFields(field.getType(), prefix, visitedPaths));
                }
            }
        }

        return ignored;
    }


    private Node filterIgnoredFields(Node node, Set<String> ignoredFields) {
        if (node instanceof LogicalNode) {
            LogicalNode logicalNode = (LogicalNode) node;
            List<Node> children = logicalNode.getChildren().stream()
                    .map(child -> filterIgnoredFields(child, ignoredFields))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());

            if (children.isEmpty()) {
                return null;
            }

            return logicalNode instanceof AndNode
                    ? new AndNode(children)
                    : new OrNode(children);
        } else if (node instanceof ComparisonNode) {
            ComparisonNode comp = (ComparisonNode) node;
            String selector = comp.getSelector();

            // nếu selector bắt đầu bằng trường bị ignore → loại bỏ
            boolean ignored = ignoredFields.stream().anyMatch(selector::startsWith);
            return ignored ? null : node;
        }
        return node;
    }
}
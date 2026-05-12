package com.VinhUniLab.controller;

import com.VinhUniLab.entity.Computer;
import com.VinhUniLab.service.ComputerService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/computers")
public class ComputerController extends BaseController<Computer, ComputerService> {
}

package com.coweco.server.controllers;

import com.coweco.server.models.Good;
import com.coweco.server.services.MainService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

	@Autowired
	MainService mainService;

	@GetMapping(value = "/avito")
	public List<Good> getAllGoodsFromAvito(){
		return mainService.getAllGoods();
	}
}

package com.Projetorevlombock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Projetorevlombock.entities.Tarefa;
import com.Projetorevlombock.service.TarefaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "tarefa", description = "API REST DE GERECIAMENTO DE tarefa")
@RestController
@RequestMapping("/tarefa")
@CrossOrigin(origins = "*")
public class TarefaController {
	
	private final TarefaService tarefaService;

	@Autowired
	public TarefaController(TarefaService tarefaService) {
		this.tarefaService = tarefaService;
	}


	@GetMapping("/{id}")
	@Operation(summary = "Localiza uma tarefa por ID")
	public ResponseEntity<Tarefa> getTarefaById(@PathVariable Long id) {
		Tarefa Tarefa = tarefaService.getTarefaById(id);
		if (Tarefa != null) {
			return ResponseEntity.ok(Tarefa);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping
	@Operation(summary = "Apresenta todas as tarefas")
	public ResponseEntity<List<Tarefa>> getAllTarefa() {
		List<Tarefa> Tarefa = tarefaService.getAllTarefa();
		return ResponseEntity.ok(Tarefa);
	}

	@PostMapping
	@Operation(summary = "Cadastra uma tarefa")
	public ResponseEntity<Tarefa> criarTarefa(@RequestBody @Valid Tarefa tarefa) {
		Tarefa criarTarefa = tarefaService.salvarTarefa(tarefa);
		return ResponseEntity.status(HttpStatus.CREATED).body(criarTarefa);
	}


	@PutMapping("/{id}")
	@Operation(summary = "Alterar uma tarefa")
	public ResponseEntity<Tarefa> updateTarefa(@PathVariable Long id, @RequestBody @Valid Tarefa tarefa) {
		Tarefa updatedTarefa = tarefaService.updateTarefa(id, tarefa);
		if (updatedTarefa != null) {
			return ResponseEntity.ok(updatedTarefa);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Deleta uma tarefa")
	public ResponseEntity<String> deleteTarefa(@PathVariable Long id) {
		boolean deleted = tarefaService.deleteTarefa(id);
		if (deleted) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}


}

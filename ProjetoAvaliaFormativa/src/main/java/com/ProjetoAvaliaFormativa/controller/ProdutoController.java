package com.ProjetoAvaliaFormativa.controller;

	import java.util.List;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.http.HttpStatus;
	import org.springframework.http.ResponseEntity;
	import org.springframework.web.bind.annotation.DeleteMapping;
	import org.springframework.web.bind.annotation.GetMapping;
	import org.springframework.web.bind.annotation.PathVariable;
	import org.springframework.web.bind.annotation.PostMapping;
	import org.springframework.web.bind.annotation.PutMapping;
	import org.springframework.web.bind.annotation.RequestBody;
	import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.bind.annotation.RestController;

	import com.ProjetoAvaliaFormativa.entites.Produto;
	import com.ProjetoAvaliaFormativa.service.ProdutoService;

	import io.swagger.v3.oas.annotations.Operation;
	import io.swagger.v3.oas.annotations.tags.Tag;
	import jakarta.validation.Valid;

	@Tag(name= "Produto", description = "API REST DE GERENCIAMENTO  DE PRODUTOS")
	@RestController
	@RequestMapping("/produto")
	public class ProdutoController {
			private final ProdutoService produtoService;

			@Autowired
			public ProdutoController(ProdutoService produtoService) {
				this.produtoService = produtoService;
			}
			@GetMapping("/{id}")
			@Operation(summary = "Localiza usuario por ID")
			public ResponseEntity<Produto> buscaProdutoControlId(@PathVariable Long id){
				Produto produto = produtoService.buscaProdutoId(id);
				if(produto !=null) {
					return ResponseEntity.ok(produto);
				}
				else {
					return ResponseEntity.notFound().build();
				}
			}
			@GetMapping("/")
			@Operation(summary = "Apresenta todos os Produtos")
			public ResponseEntity<List<Produto>> buscaTodosProdutoControl(){
				List<Produto> Produto = produtoService.buscaTodosProduto();
				return ResponseEntity.ok(Produto);
			}
			@PostMapping("/")
			@Operation(summary = "Cadastra um Produto")
			public ResponseEntity<Produto> salvaProdutoControl(@RequestBody @Valid Produto produto){
				Produto salvaProduto = produtoService.salvaProduto(produto);
				return ResponseEntity.status(HttpStatus.CREATED).body(salvaProduto);
		}
			@PutMapping("/{id}")
			@Operation(summary= "Altera Produto")
			public ResponseEntity<Produto> alteraProdutoControl(@PathVariable Long id, @RequestBody @Valid Produto produto){
				Produto alteraProduto = produtoService.alterarProduto(id, produto);
				if(alteraProduto !=null) {
					return ResponseEntity.ok(produto);
				}
				else {
					return ResponseEntity.notFound().build();
				}
				
			}
			@DeleteMapping("/{id}")
			@Operation (summary = "Deleta Produto")
			public ResponseEntity<Produto> apagaProdutoControl(@PathVariable Long id){
				boolean apagar = produtoService.apagarProduto(id);
				if(apagar) {
					return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
				}
				else {
					return ResponseEntity.notFound().build();
				}
			}
	}

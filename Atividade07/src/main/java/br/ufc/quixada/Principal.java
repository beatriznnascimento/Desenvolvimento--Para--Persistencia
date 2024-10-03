package br.ufc.quixada;

import java.util.List;

import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import br.ufc.quixada.dao.ProdutoDAO;
import br.ufc.quixada.entity.Produto;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class Principal implements CommandLineRunner {

	@Autowired
	private ProdutoDAO baseProdutos;

	public static void main(String[] args) {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(Principal.class);
        builder.headless(false).run(args);
	}

	@Override
	public void run(String... args) {
		String menu = "Escolha uma opção:\n1 - Inserir\n2 - Atualizar por código\n3 - Remover por código\n4 - Exibir por código\n5 - Exibir por id\n6 - Exibir todos\n7 - Exibir todos que contêm determinado código\n8 - Sair";
		char opcao = '0';
		do {
			try {
				Produto prod;
				String codigo;
				opcao = JOptionPane.showInputDialog(menu).charAt(0);
				switch (opcao) {
				case '1':     // Inserir
					prod = new Produto();
					obterProduto(prod);
					baseProdutos.save(prod);
					break;
				case '2':     // Atualizar por Código
					codigo = JOptionPane.showInputDialog("Digite o código do produto a ser alterado");
					prod = baseProdutos.findByCodigo(codigo);
					if (prod != null) {
						obterProduto(prod);
						baseProdutos.save(prod);
					} else {
						JOptionPane.showMessageDialog(null, "Não foi possível atualizar, pois o produto não foi encontrado.");
					}
					break;
				case '3':     // Remover por Código
					codigo = JOptionPane.showInputDialog("Código");
					prod = baseProdutos.findByCodigo(codigo);
					if (prod != null) {
						baseProdutos.delete(prod.getId());
					} else {
						JOptionPane.showMessageDialog(null, "Não foi possível remover, pois o produto não foi encontrado.");
					}
					break;
				case '4':     // Exibir por Código
					codigo = JOptionPane.showInputDialog("Código");
					prod = baseProdutos.findByCodigo(codigo);
					listaProduto(prod);
					break;
				case '5':     // Exibir por id
					int id = Integer.parseInt(JOptionPane.showInputDialog("Id"));
					prod = baseProdutos.find(id);
					listaProduto(prod);
					break;
				case '6':     // Exibir todos
					listaProdutos(baseProdutos.find());
					break;
				case '7':     // Exibir todos que contêm determinado código
					String codigoConsulta = JOptionPane.showInputDialog("Código");
					listaProdutos(baseProdutos.findByCodigo(codigoConsulta));
					break;
				case '8':     // Sair
					break;
				default:
					JOptionPane.showMessageDialog(null, "Opção Inválida");
					break;
				}
			} catch (NumberFormatException e) {
				log.error("Erro: {}", e.getMessage(), e);
				JOptionPane.showMessageDialog(null, "Entrada inválida: " + e.getMessage());

			} catch (Exception e) {
				log.error("Erro: {}", e.getMessage(), e);
				JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
			}
		} while(opcao != '8');	
	}
	
	public static void obterProduto(Produto prod) {
		String codigo = JOptionPane.showInputDialog("Código", prod.getCodigo());
		Float preco = Float.parseFloat(JOptionPane.showInputDialog("Preço", prod.getPreco()));
		Integer qtdEstoque = Integer.parseInt(JOptionPane.showInputDialog("Quantidade em Estoque", prod.getQtdEstoque()));
		String dt_ult_entrada = JOptionPane.showInputDialog("Data da Última Entrada", prod.getDt_ult_entrada());
		prod.setCodigo(codigo);
		prod.setPreco(preco);
		prod.setQtdEstoque(qtdEstoque);
		prod.setDt_ult_entrada(dt_ult_entrada);
	}

	public static void listaProdutos(List<Produto> produtos) {
		StringBuilder listagem = new StringBuilder();
		for(Produto prod : produtos) {
			listagem.append(prod).append("\n");
		}
		JOptionPane.showMessageDialog(null, listagem.length() == 0 ? "Nenhum produto encontrado" : listagem);
	}

	public static void listaProduto(Produto prod) {
		JOptionPane.showMessageDialog(null, prod == null ? "Nenhum produto encontrado" : prod);
	}
}

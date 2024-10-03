package entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Produto {
    
    private int id;
    private String codigo;
    private String descricao;
    private double preco;
    private String dtUltima;

}

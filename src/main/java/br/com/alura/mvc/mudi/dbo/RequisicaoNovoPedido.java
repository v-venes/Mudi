package br.com.alura.mvc.mudi.dbo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.StatusPedido;

public class RequisicaoNovoPedido {

    @NotBlank @Size(min = 20, max = 255)
    private String nomeProduto;

    @NotBlank @Size(min = 20, max = 255)
    private String urlProduto;
    
    @NotBlank @Size(min = 20, max = 255)
    private String urlImagem;

    @Size(min = 0, max = 255)    
    private String descricao;

    public String getNomeProduto() {
        return this.nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getUrlProduto() {
        return this.urlProduto;
    }

    public void setUrlProduto(String urlProduto) {
        this.urlProduto = urlProduto;
    }

    public String getUrlImagem() {
        return this.urlImagem;
    }

    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Pedido toPedido() {
        Pedido pedido = new Pedido();
        pedido.setDescricao(descricao);
        pedido.setNomeProduto(nomeProduto);
        pedido.setUrlProduto(urlProduto);
        pedido.setUrlImagem(urlImagem);
        pedido.setStatus(StatusPedido.AGUARDANDO);
        return pedido;
    }

}

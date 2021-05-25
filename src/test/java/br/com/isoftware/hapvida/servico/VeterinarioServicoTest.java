package br.com.isoftware.hapvida.servico;

import org.springframework.boot.test.context.SpringBootTest;

import br.com.isoftware.hapvida.entidade.Veterinario;
import br.com.isoftware.hapvida.enums.Operacao;

@SpringBootTest
public class VeterinarioServicoTest extends GenericoServicoTest<Integer, Veterinario, VeterinarioServico> {

    @Override
    Veterinario montarEntidade(Veterinario entidade, Operacao operacao) {
        if (Operacao.CRIAR.equals(operacao) || Operacao.DELETAR.equals(operacao)) {
            Veterinario veterinario = new Veterinario();
            veterinario.setNome("Ednardo ".concat(operacao.name()));
            veterinario.setEmail("ednardo994@gmail.com");
            veterinario.setTelefone("85999496331");
            return veterinario;
        }

        entidade.setNome("Ed ".concat(operacao.name()));

        return entidade;
    }
}

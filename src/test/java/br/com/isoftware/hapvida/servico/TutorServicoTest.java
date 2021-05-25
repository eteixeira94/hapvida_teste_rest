package br.com.isoftware.hapvida.servico;

import org.springframework.boot.test.context.SpringBootTest;

import br.com.isoftware.hapvida.entidade.Tutor;
import br.com.isoftware.hapvida.enums.Operacao;

@SpringBootTest
public class TutorServicoTest extends GenericoServicoTest<Integer, Tutor, TutorServico> {

    @Override
    Tutor montarEntidade(Tutor entidade, Operacao operacao) {
        if (Operacao.CRIAR.equals(operacao) || Operacao.DELETAR.equals(operacao)) {
            Tutor tutor = new Tutor();
            tutor.setNome("Ednardo ".concat(operacao.name()));
            tutor.setEmail("ednardo994@gmail.com");
            tutor.setTelefone("85999496331");
            return tutor;
        }

        entidade.setNome("Ed ".concat(operacao.name()));

        return entidade;
    }
}

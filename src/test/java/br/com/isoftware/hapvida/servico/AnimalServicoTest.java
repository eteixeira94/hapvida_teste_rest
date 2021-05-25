package br.com.isoftware.hapvida.servico;

import java.text.SimpleDateFormat;

import org.springframework.boot.test.context.SpringBootTest;

import br.com.isoftware.hapvida.entidade.Animal;
import br.com.isoftware.hapvida.entidade.enums.RacaoEnum;
import br.com.isoftware.hapvida.enums.Operacao;
import lombok.SneakyThrows;

@SpringBootTest
public class AnimalServicoTest extends GenericoServicoTest<Integer, Animal, AnimalServico> {

    @Override
    @SneakyThrows
    Animal montarEntidade(Animal entidade, Operacao operacao) {
        if (Operacao.CRIAR.equals(operacao) || Operacao.DELETAR.equals(operacao)) {
            Animal animal = new Animal();
            animal.setDataNascimento(new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2021"));
            animal.setEspecie("CÃO");
            animal.setNome("Juju ".concat(operacao.name()));
            animal.setRaca(RacaoEnum.PASTOR_ALEMAO);
            return animal;
        }

        entidade.setNome("Juju ".concat(operacao.name()));

        return entidade;
    }
}

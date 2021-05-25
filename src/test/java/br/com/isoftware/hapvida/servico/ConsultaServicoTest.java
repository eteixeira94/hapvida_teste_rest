package br.com.isoftware.hapvida.servico;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

import java.text.SimpleDateFormat;
import java.util.HashSet;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.isoftware.hapvida.entidade.Animal;
import br.com.isoftware.hapvida.entidade.Consulta;
import br.com.isoftware.hapvida.entidade.ConsultaHasAnimal;
import br.com.isoftware.hapvida.entidade.ConsultaHasAnimalId;
import br.com.isoftware.hapvida.entidade.Veterinario;
import br.com.isoftware.hapvida.entidade.enums.RacaoEnum;
import br.com.isoftware.hapvida.servico.AnimalServico;
import br.com.isoftware.hapvida.servico.ConsultaServico;
import br.com.isoftware.hapvida.servico.VeterinarioServico;
import lombok.SneakyThrows;

@SpringBootTest
public class ConsultaServicoTest {

    @Autowired
    private AnimalServico animalServico;

    @Autowired
    private VeterinarioServico veterinarioServico;

    @Autowired
    private ConsultaServico consultaServico;

    @Test
    @SneakyThrows
    public void deveSalvarConsulta_ComSucesso() {
        Veterinario veterinario = criarVeterinario();
        Animal animal = criarAnimal();
        Consulta consulta = montarConsulta(veterinario, animal);

        this.consultaServico.salvar(consulta);

        assertThat(consulta.getId(), notNullValue());
    }

    @SneakyThrows
    private Consulta montarConsulta(Veterinario veterinario, Animal animal) {
        Consulta consulta = new Consulta();
        consulta.setDataConsulta(new SimpleDateFormat("dd/MM/yyyy").parse("25/05/2021"));
        consulta.setConsultaHasAnimals(new HashSet<>());

        ConsultaHasAnimal item = new ConsultaHasAnimal();
        item.setId(new ConsultaHasAnimalId(null, animal.getId(), veterinario.getId()));
        consulta.getConsultaHasAnimals().add(item);

        return consulta;
    }

    @SneakyThrows
    private Animal criarAnimal() {
        Animal animal = new Animal();
        animal.setDataNascimento(new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2021"));
        animal.setEspecie("CÃO");
        animal.setNome("Juju");
        animal.setRaca(RacaoEnum.PASTOR_ALEMAO);

        this.animalServico.salvar(animal);

        return animal;
    }

    private Veterinario criarVeterinario() {
        Veterinario veterinario = new Veterinario();
        veterinario.setNome("Ednardo Teixeira");
        veterinario.setEmail("ednardo994@gmail.com");
        veterinario.setTelefone("85999496331");

        this.veterinarioServico.salvar(veterinario);

        return veterinario;
    }
}

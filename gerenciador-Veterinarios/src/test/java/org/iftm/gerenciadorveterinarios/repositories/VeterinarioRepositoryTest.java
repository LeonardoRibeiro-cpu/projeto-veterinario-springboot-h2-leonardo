package org.iftm.gerenciadorveterinarios.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.util.List;

import org.iftm.gerenciadorveterinarios.entities.Veterinario;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


@DataJpaTest
public class VeterinarioRepositoryTest {


    @Autowired
    private VeterinarioRepository repository;

    @Test
    public void testeBuscarVeterinarioComIdExistenteCorretamente(){
    
        int idExistente = 1;
        String nomeEsperado = "Conceição Evaristo";
        String emailEsperado = "conceicao@gmail.com";

 
       Veterinario veterinarioEncontrado = repository.getById(idExistente);

  
       assertNotNull(veterinarioEncontrado);
       assertEquals(nomeEsperado, veterinarioEncontrado.getNome());
       assertEquals(emailEsperado, veterinarioEncontrado.getEmail());

    }

    @Test
    public void testeBuscaDeVeterinarioExistentePeloNomeMinusculoSemCaseSensentiveDeveFalhar(){

        String nomeEsperado = "marcos oliveira";

        int quantidadeEsperada = 0;

        List<Veterinario> veterinarioExistentes = repository.findByNome(nomeEsperado);
        int quantidadeExistente = veterinarioExistentes.size();

       assertNotNull(veterinarioExistentes);
       assertEquals(quantidadeEsperada, quantidadeExistente);

    }

    @Test
    public void testeBuscaDeVeterinarioExistentePeloNomeMaiusculoSemCaseSensentiveDeveFalhar(){

        String nomeEsperado = "MARCOS OLIVEIRA";
        int quantidadeEsperada = 0;

        List<Veterinario> veterinarioExistentes = repository.findByNome(nomeEsperado);
        int quantidadeExistente = veterinarioExistentes.size();

       assertNotNull(veterinarioExistentes);
       assertEquals(quantidadeEsperada, quantidadeExistente);
 
    }

    @Test
    public void testeBuscaDeVeterinarioNãoExistenteComCaseSensitiveDeveFalhar(){

        String nomeEsperado = "giovanny morais";
        int quantidadeEsperada = 0;

        List<Veterinario> veterinarioExistentes = repository.findByNome(nomeEsperado);
        int quantidadeExistente = veterinarioExistentes.size();

       assertNotNull(veterinarioExistentes);
       assertEquals(quantidadeEsperada, quantidadeExistente);

    }

    @Test
    public void testeDeBuscaDeVeterinarioExistenteComCaseSensitive(){
    String nomeEsperado = "amanDa RIbeiro" ;
    int quantidadeEsperada = 1;

    List<Veterinario> veterinarioExistentes = repository.findByNomeIgnoreCase(nomeEsperado);
        int quantidadeExistente = veterinarioExistentes.size();

       assertNotNull(veterinarioExistentes);
       assertEquals(quantidadeEsperada, quantidadeExistente);

    
    }

    @Test
    public void testeDeBuscaDeNomePelaSilabaTesteDeveFalhar(){
        String nomeExistente = "co";

        String nomeEsperado = "Conceição Evaristo";
        String nomeEsperado2 = "Marcos Oliveira";
        int quantidadeEsperada = 2;



        List<Veterinario> veterinarioExistentes = repository.findByNomeContainsIgnoreCase(nomeExistente);
        int quantidadeExistente = veterinarioExistentes.size();

       assertNotNull(veterinarioExistentes);
       assertEquals(quantidadeEsperada, quantidadeExistente);
       assertEquals(nomeEsperado2, veterinarioExistentes);
       assertEquals(nomeEsperado, veterinarioExistentes);

    }

    @Test 
    public void testeDeBuscaDoVeterinarioBrunoPelaSilaba(){
        String nomeEsperado = "Bruno";
        int quantidadeEsperada = 1;

        List<Veterinario> veterinarioExistente = repository.findByNomeContainsIgnoreCase(nomeEsperado);
        int quantidadeExistente = veterinarioExistente.size();
    
       assertNotNull(veterinarioExistente);
       assertEquals(quantidadeEsperada, quantidadeExistente);

    }

    @Test
    public void testeDeBuscaDeParametroComStringVazia(){
        String nomeEsperado = "";

        int quantidadeEsperada = 12;

        List<Veterinario> veterinarioExistente = repository.findByNomeContainsIgnoreCase(nomeEsperado);
        int quantidadeExistente = veterinarioExistente.size();
    
       assertNotNull(veterinarioExistente);
       assertEquals(quantidadeEsperada, quantidadeExistente);

    }

    @Test
    public void testeDeBuscaDeSalarioSuperiorAoValorInformado(){

        Double SalarioEsperado = 3000.0;
        int quantidadeEsperada = 10;

        List<Veterinario> veterinarioExistente = repository.findBySalarioGreaterThan(BigDecimal.valueOf(SalarioEsperado));
        int quantidadeExistente = veterinarioExistente.size();
    
       assertNotNull(veterinarioExistente);
       assertEquals(quantidadeEsperada, quantidadeExistente);

    }

    @Test
    public void testeDeBuscaDeSalarioInferiorAoValorInformado() {
       
        BigDecimal salarioReferencia = BigDecimal.valueOf(3000.0);
        int quantidadeEsperada = 10;
 
        List<Veterinario> veterinariosEncontrados = repository.findBySalarioLessThan(salarioReferencia);
 
        // Assert
        assertNotNull(veterinariosEncontrados);
        assertEquals(quantidadeEsperada, veterinariosEncontrados.size());
    }

    @Test
    public void testeDeBuscaSalarioEntreOsValorInformado(){

        Double SalarioEsperado = 3000.0;
        Double SalarioEsperado2 = 5000.0;

        int quantidadeEsperada = 7;

        List<Veterinario> veterinarioExistente = repository.findBySalarioBetween(BigDecimal.valueOf(SalarioEsperado),BigDecimal.valueOf(SalarioEsperado2));
        int quantidadeExistente = veterinarioExistente.size();
    
       assertNotNull(veterinarioExistente);
       assertEquals(quantidadeEsperada, quantidadeExistente);

    }

    @Test
    public void testeValidaUpdate(){
        int idSerAlterado = 1;
        String nomeAlterado = "Leonardo";
        BigDecimal  salarioAlterado = BigDecimal.valueOf(2800.0);
        
        Veterinario veterinarioExistente  = repository.findById(idSerAlterado).get();

        veterinarioExistente.setNome(nomeAlterado);
        veterinarioExistente.setSalario(salarioAlterado);

        repository.save(veterinarioExistente);

        assertEquals(nomeAlterado, veterinarioExistente.getNome());


    }

}

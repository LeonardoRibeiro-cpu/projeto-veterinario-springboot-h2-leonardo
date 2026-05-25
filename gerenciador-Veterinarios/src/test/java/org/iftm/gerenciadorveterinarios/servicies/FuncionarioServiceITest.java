package org.iftm.gerenciadorveterinarios.servicies;


import org.iftm.gerenciadorveterinarios.entities.Funcionario;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FuncionarioServiceITest {
    

    @Autowired
    private FuncionarioService service;

    @Test
    public void testFuncionarioComStatusPadrao(){
        //arrenge
        Funcionario funcionarioEntrada = new Funcionario(1,"Leonardo Andrade","gerente",5000.00,false,false);
      
        
     

        // act
       Funcionario resultado = service.salvar(funcionarioEntrada);

     // assert
     assertTrue(resultado.isAtivo()); 
     assertEquals("Leonardo Andrade", resultado.getNome());
     assertEquals("gerente", resultado.getCargo());
     assertEquals(5000.00, resultado.getSalario());

    }
    
    @Test
    public void testarNaoDeveSalvarFuncionarioComSalarioInvalido() {

    // arrange
    Funcionario funcionario = new Funcionario(1, "Leonardo Andrade", "gerente", -1000.00, false, true);

    // act 
    assertThrows(IllegalArgumentException.class, () -> {
        service.salvar(funcionario);
    });

  }

  @Test
   public void testarConcederFeriasComSucesso() {


   // act
   service.concederFerias(1);

   // assert
   Funcionario atualizado = service.buscarPorId(1);
   assertTrue(atualizado.isEmFerias());

    }
}


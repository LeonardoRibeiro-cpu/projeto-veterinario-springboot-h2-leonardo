/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package org.iftm.gerenciadorveterinarios.servicies;

import org.iftm.gerenciadorveterinarios.entities.Funcionario;
import org.iftm.gerenciadorveterinarios.repositories.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class FuncionarioService {

    @Autowired
    private FuncionarioRepository repository;

  public Funcionario salvar(Funcionario funcionarioEntrada) {

    if (funcionarioEntrada.getSalario() < 0) {
        throw new IllegalArgumentException("Salário inválido");
    }

    funcionarioEntrada.setAtivo(true);

    return repository.save(funcionarioEntrada);
}
  
public void concederFerias(Integer id) {

    Funcionario funcionario = repository.findById(id)
        .orElseThrow(() -> new RuntimeException("Funcionario não encontrado"));

    funcionario.setEmFerias(true);

    repository.save(funcionario);
}
    
}
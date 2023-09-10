package br.com.alura.logs.repository;

import br.com.alura.logs.model.CursoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CursoRepository extends JpaRepository<CursoModel, UUID> {

    boolean existsByNumeroMatricula(String numeroMatricula);

    boolean existsByNumeroCurso(String numeroCurso);

}

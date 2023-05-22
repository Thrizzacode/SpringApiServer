package server.api.repository;

import org.springframework.data.repository.CrudRepository;
import server.api.model.Member;

import java.util.List;

public interface MemberRepository extends CrudRepository<Member, String> {

//    Iterable<Member> findAll();
    List<Member> findAll();
   void deleteById(Long id);


}

package server.api.repository;

import org.springframework.data.repository.CrudRepository;
import server.api.model.Member;

public interface MemberRepository extends CrudRepository<Member, String> {

    Iterable<Member> findAll();
   void deleteById(Long id);


}

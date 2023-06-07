package server.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import server.api.model.Member;

import java.util.List;

public interface MemberRepository extends PagingAndSortingRepository<Member, String> {

    Member findById(Long id);

   void deleteById(Long id);


}

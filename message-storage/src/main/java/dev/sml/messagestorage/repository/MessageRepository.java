package dev.sml.messagestorage.repository;

import dev.sml.messagestorage.entities.MessageModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MessageRepository extends PagingAndSortingRepository<MessageModel, Integer> {
    //решение проблемы N+1 получаем все одним запросом
    @Query(value = "from MessageModel m join fetch m.user  where m.user.name = :userName",
            countQuery = "select count(m) from MessageModel m where m.user.name = :userName")
    Page<MessageModel> findAllByUserName(String userName, Pageable pageable);

    @Query(value = "from MessageModel m join fetch m.user",
            countQuery = "select count(m) from MessageModel m")
    Page<MessageModel> findAll(Pageable pageable);

}
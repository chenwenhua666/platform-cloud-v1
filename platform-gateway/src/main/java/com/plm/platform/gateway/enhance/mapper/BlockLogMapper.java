package com.plm.platform.gateway.enhance.mapper;

import com.plm.platform.gateway.enhance.entity.BlockLog;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

/**
 *
 */
public interface BlockLogMapper extends ReactiveMongoRepository<BlockLog, String> {

    /**
     * 删除拦截日志
     *
     * @param ids 日志id
     * @return 被删除的拦截日志
     */
    Flux<BlockLog> deleteByIdIn(String[] ids);
}

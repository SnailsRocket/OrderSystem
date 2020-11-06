package com.xubo.modules.common.base;

import java.text.ParseException;
import java.util.List;

/**
 * 工具类接口
 *  DTO 转entity
 *  entity 转 DTO
 * @param <D>
 * @param <E>
 */
public interface BaseTranslate<D,E> {

    /**
     *
     * @param dto
     * @return
     */
    E toEntity(D dto) throws ParseException;

    /**
     *
     * @param entity
     * @return
     */
    D toDto(E entity);

    /**
     *
     * @param dtoList
     * @return
     */
    List<E> toEntity(List<D> dtoList);

    /**
     *
     * @param entityList
     * @return
     */
    List<D> toDto(List<E> entityList);

}

package com.example.demo.mapper;

public interface GenericMapper<D, E> {

    D toDto(E e);

    E toEntity(D d);

//    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//    void updateFromDto(D dto, @MappingTarget E entity);
}

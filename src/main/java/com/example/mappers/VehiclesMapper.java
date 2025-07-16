package com.example.mappers;

import com.example.vehicles.model.Vehicles;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VehiclesMapper {

    VehiclesDto toDto(Vehicles vehicles);

    List<VehiclesDto> toDtoList(List<Vehicles> vehicles);
}

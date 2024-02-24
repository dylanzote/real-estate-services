package com.zote.property.service.api;

import com.zote.property.service.api.enums.SortField;
import com.zote.property.service.api.request.CreatePropertyRequest;
import com.zote.property.service.api.request.UpdatePropertyRequest;
import com.zote.property.service.api.response.PropertyPageResponse;
import com.zote.common.utls.models.ResponseData;
import com.zote.property.service.domain.models.Property;
import com.zote.property.service.domain.ports.inbound.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/property")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
public class PropertyApi {

    private final CreateProperty createProperty;
    private final GetAllPropertyByPage getAllPropertyByPage;
    private final GetPropertyById getPropertyById;
    private final UpdateProperty updateProperty;
    private final DeleteProperty deleteProperty;

    @PostMapping("/create")
    public Property createProperty(@RequestBody CreatePropertyRequest createPropertyRequest) {
        log.info("incoming creat property request with data {}", createPropertyRequest);
        return createProperty.createProperty(createPropertyRequest.toProperty());
    }

    @GetMapping("/getAllByPage")
    public PropertyPageResponse getAllPropertiesByPage(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                                       @RequestParam(name = "sizePerPage", defaultValue = "5") Integer sizePerPage,
                                                       @RequestParam(name = "sortField", defaultValue = "CREATED_ON") SortField sortField,
                                                       @RequestParam(name = "sortDirection", defaultValue = "DESC") Sort.Direction sortDirection) {
        log.info("incoming get all properties by page request with page {}, sizePerPage {}, SortField {}, SortOrder {}", pageNo, sizePerPage, sortField, sortDirection);
        return new PropertyPageResponse(getAllPropertyByPage.getAllPropertiesByPage(pageNo, sizePerPage, sortField.getFieldName(), sortDirection));
    }

    @GetMapping("/getById/{propertyId}")
    public Property findPropertyById(@PathVariable("propertyId") String propertyId) {
        log.info("incoming get property by id request with id {}", propertyId);
        return getPropertyById.findPropertyById(propertyId);
    }

    @PostMapping("/update")
    public Property updateProperty(@RequestBody UpdatePropertyRequest updatePropertyRequest) {
        log.info("incoming update property request with data {}", updatePropertyRequest);
        return updateProperty.updateProperty(updatePropertyRequest.toDomain());
    }

    @DeleteMapping("/delete/{propertyId}")
    public ResponseData deleteProperty(@PathVariable("propertyId") String propertyId) {
        log.info("incoming delete property request with id {}", propertyId);
        deleteProperty.deleteProperty(propertyId);
        return new ResponseData(200, "successful");
    }
}

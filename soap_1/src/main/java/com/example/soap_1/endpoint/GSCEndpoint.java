package com.example.soap_1.endpoint;

import com.example.soap_1.model.GSC;
import com.example.soap_1.service.GSCRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import soap_1.example.com.soap_gen.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Endpoint
public class GSCEndpoint {

    public static final String NAMESPACE_URI = "http://com.example.soap_1/soap_gen";

    private GSCRepository gscRepository;

    public GSCEndpoint() {

    }

    @Autowired
    public GSCEndpoint(GSCRepository gscRepository) {
        this.gscRepository = gscRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getGSCByNameRequest")
    @ResponsePayload
    public GetGSCByNameResponse getGSCByName(@RequestPayload GetGSCByNameRequest request) throws SQLException {
        GetGSCByNameResponse response = new GetGSCByNameResponse();
        GSC gscEntity = gscRepository.getGSCByName(request.getAttributeName());
        GSCType gscType = new GSCType();
        BeanUtils.copyProperties(gscEntity, gscType);
        response.setGSCType(gscType);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllGSCsRequest")
    @ResponsePayload
    public GetAllGSCsResponse getAllGSCs(@RequestPayload GetAllGSCsRequest request) throws SQLException {
        GetAllGSCsResponse response = new GetAllGSCsResponse();
        List<GSCType> gscTypeList = new ArrayList<GSCType>();
        List<GSC> gscEntityList = gscRepository.getAllGSC();
        for (GSC gsc : gscEntityList) {
            GSCType gscType = new GSCType();
            BeanUtils.copyProperties(gsc, gscType);
            gscTypeList.add(gscType);
        }
        response.getGSCType().addAll(gscTypeList);

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addGSCRequest")
    @ResponsePayload
    public AddGSCResponse addGSC(@RequestPayload AddGSCRequest request) throws SQLException {
        AddGSCResponse response = new AddGSCResponse();
        GSCType newGSCType = new GSCType();
        ServiceStatus serviceStatus = new ServiceStatus();

        GSC newGSCEntity = new GSC(request.getAttributeName(), request.getAttributeValue(),
                request.getAttributeDesc());
        GSC savedGSCEntity = gscRepository.addGSC(newGSCEntity);

        if (savedGSCEntity == null) {
            serviceStatus.setStatusCode("CONFLICT");
            serviceStatus.setMessage("Exception while adding Entity");
        } else {
            BeanUtils.copyProperties(savedGSCEntity, newGSCType);
            serviceStatus.setStatusCode("SUCCESS");
            serviceStatus.setMessage("Content Added Successfully");
        }

        response.setGSCType(newGSCType);
        response.setServiceStatus(serviceStatus);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateGSCRequest")
    @ResponsePayload
    public UpdateGSCResponse updateGSC(@RequestPayload UpdateGSCRequest request) throws SQLException {
        UpdateGSCResponse response = new UpdateGSCResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        // 1. Find if GSC available
        GSC gscFromDB = gscRepository.getGSCByName(request.getAttributeName());

        if(gscFromDB == null) {
            serviceStatus.setStatusCode("NOT FOUND");
            serviceStatus.setMessage("GSC = " + request.getAttributeName() + " not found");
        } else {

            // 2. Get updated movie information from the request
            gscFromDB.setAttributeValue(request.getAttributeValue());
            gscFromDB.setAttributeDesc(request.getAttributeDesc());
            // 3. update the movie in database

            boolean flag = gscRepository.updateGSC(gscFromDB, gscFromDB.getAttributeName());

            if(flag == false) {
                serviceStatus.setStatusCode("CONFLICT");
                serviceStatus.setMessage("Exception while updating Entity=" + request.getAttributeName());;
            }else {
                serviceStatus.setStatusCode("SUCCESS");
                serviceStatus.setMessage("Content updated Successfully");
            }
        }

        response.setServiceStatus(serviceStatus);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteGSCRequest")
    @ResponsePayload
    public DeleteGSCResponse deleteGSC(@RequestPayload DeleteGSCRequest request) throws SQLException {
        DeleteGSCResponse response = new DeleteGSCResponse();
        ServiceStatus serviceStatus = new ServiceStatus();

        boolean flag = gscRepository.deleteGSC(request.getAttributeName());

        if (flag == false) {
            serviceStatus.setStatusCode("FAIL");
            serviceStatus.setMessage("Exception while deleting GSC =" + request.getAttributeName());
        } else {
            serviceStatus.setStatusCode("SUCCESS");
            serviceStatus.setMessage("Content Deleted Successfully");
        }

        response.setServiceStatus(serviceStatus);
        return response;

    }
}

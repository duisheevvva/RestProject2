package peaksoft.service;
import peaksoft.dto.request.CompanyRequest;
import peaksoft.dto.response.CompanyResponse;
import peaksoft.dto.response.simple.SimpleResponse;
import peaksoft.exception.MyException;

import java.util.List;

public interface CompanyService {


    CompanyResponse saveCompany(CompanyRequest companyRequest);

    List<CompanyResponse> getAllCompany();

    CompanyResponse getByIdCompany(Long id) throws MyException;

    CompanyResponse updateCompanyById(Long id,CompanyRequest companyRequest) throws MyException;

    SimpleResponse deleteById(Long id) throws MyException;

}

package peaksoft.service.serviceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.CompanyRequest;
import peaksoft.dto.response.CompanyResponse;
import peaksoft.dto.response.simple.SimpleResponse;
import peaksoft.entity.Company;
import peaksoft.exception.MyException;
import peaksoft.repository.CompanyRepository;
import peaksoft.service.CompanyService;
import java.util.List;


@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    @Override
    public CompanyResponse saveCompany(CompanyRequest companyRequest) {
        Company company1 = new Company();
        company1.setName(companyRequest.getName());
        company1.setAddress(companyRequest.getAddress());
        company1.setPhoneNumber(companyRequest.getPhoneNumber());
        companyRepository.save(company1);
        return new CompanyResponse(
                company1.getId(),
                company1.getName(),
                company1.getAddress(),
                company1.getPhoneNumber()
        );
    }

    @Override
    public List<CompanyResponse> getAllCompany() {
        return companyRepository.getAllCompany();
    }



    @Override
    public CompanyResponse getByIdCompany(Long id) throws MyException {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new MyException("Company with id:" + id + "is not found"));
        return new CompanyResponse(
                company.getId(),
                company.getName(),
                company.getAddress(),
                company.getPhoneNumber()
        );
    }




    @Override
    public CompanyResponse updateCompanyById(Long id, CompanyRequest companyRequest) throws MyException {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new MyException("Company with id:" + id + "is not found"));
        company.setName(companyRequest.getName());
        company.setAddress(companyRequest.getAddress());
        company.setPhoneNumber(companyRequest.getPhoneNumber());
        companyRepository.save(company);
        return new CompanyResponse(
                company.getId(),
                company.getName(),
                company.getAddress(),
                company.getPhoneNumber()
        );
    }

    @Override
    public SimpleResponse deleteById(Long id) throws MyException {
        companyRepository.findById(id)
                .orElseThrow(() -> new MyException("Company with id:" + id + "is not found"));
        companyRepository.deleteById(id);
        return   SimpleResponse.builder()
                .status(HttpStatus.OK)
                .massage("successfully deleted!")
                .build();
    }
}

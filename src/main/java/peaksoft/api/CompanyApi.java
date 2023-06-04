package peaksoft.api;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.CompanyRequest;
import peaksoft.dto.response.CompanyResponse;
import peaksoft.dto.response.simple.SimpleResponse;
import peaksoft.exception.MyException;
import peaksoft.service.CompanyService;
import java.util.List;

@RestController
@RequestMapping("/companies")
@RequiredArgsConstructor
public class CompanyApi {
    private final CompanyService companyService;

    @GetMapping
    public List<CompanyResponse> getAllCompanies(){
        return companyService.getAllCompany();
    }

    @PostMapping()
    public CompanyResponse saveCompany(@Valid @RequestBody CompanyRequest companyRequest ){
        return companyService.saveCompany(companyRequest);
    }

    @GetMapping("/{id}")
    public CompanyResponse getCompanyById(@PathVariable Long id) throws MyException {
        return companyService.getByIdCompany(id);
    }

    @PostMapping("/{id}")
    public CompanyResponse updateCompany(@PathVariable Long id,
                                         @Valid @RequestBody CompanyRequest companyRequest) throws MyException {
        return companyService.updateCompanyById(id,companyRequest);
    }

    @DeleteMapping ("/{id}")
    public SimpleResponse deleteCompany(@PathVariable Long id) throws MyException {
        return companyService.deleteById(id);
    }

}

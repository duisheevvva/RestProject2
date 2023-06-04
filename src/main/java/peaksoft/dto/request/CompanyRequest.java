package peaksoft.dto.request;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class CompanyRequest {
    @NotEmpty(message = "fill in the field")
    @Column(unique = true)
    private String name;
    @NotEmpty(message = "fill in the field")
    private String address;
    @NotEmpty(message = "fill in the field")
    @Pattern(regexp = "\\+996\\d{9}",message = "wrong format")
    private String phoneNumber;


    public CompanyRequest(String name, String address, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
}

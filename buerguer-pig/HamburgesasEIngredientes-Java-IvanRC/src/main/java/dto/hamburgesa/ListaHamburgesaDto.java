package dto.hamburgesa;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Root(name = "hamburgesas")
public class ListaHamburgesaDto {
    @ElementList(name = "hamburgesas", inline = true)
    public List<HamburgesaDto> hamburgesasDto;
}

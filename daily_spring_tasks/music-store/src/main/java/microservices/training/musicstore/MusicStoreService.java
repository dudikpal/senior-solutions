package microservices.training.musicstore;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class MusicStoreService {

    private List<Instrument> instruments = new ArrayList<>();

    private AtomicLong idGenerator = new AtomicLong();

    private ModelMapper modelMapper;

    public MusicStoreService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public List<InstrumentDTO> getInstruments(Optional<String> brand, Optional<Integer> price) {
        return instruments.stream()
                .filter(instr -> brand.isEmpty() || instr.getBrand().equalsIgnoreCase(brand.get()))
                .filter(instr -> price.isEmpty() || instr.getPrice() == price.get())
                .map(item ->modelMapper.map(item, InstrumentDTO.class))
                .collect(Collectors.toList());
    }

    public Instrument findById(long id) {
        return instruments.stream()
                .filter(instr -> instr.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException());
    }

    public InstrumentDTO getInstrument(long id) {
        return modelMapper.map(findById(id), InstrumentDTO.class);
    }

    public InstrumentDTO createInstrument(CreateInstrumentCommand command) {
        Instrument instrument = new Instrument(
                idGenerator.incrementAndGet(),
                command.getBrand(),
                command.getType(),
                command.getPrice(),
                LocalDate.now());
        instruments.add(instrument);
        return modelMapper.map(instrument, InstrumentDTO.class);
    }

    public void deleteAll() {
        idGenerator = new AtomicLong();
        instruments.clear();
    }


    public InstrumentDTO updatePrice(long id, UpdatePriceCommand command) {
        Instrument instrument = findById(id);
        if (instrument.getPrice() != command.getPrice()) {
            instrument.setPrice(command.getPrice());
            instrument.setPostDate(LocalDate.now());
        }

        return modelMapper.map(instrument, InstrumentDTO.class);
    }
}

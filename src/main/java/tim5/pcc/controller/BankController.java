package tim5.pcc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tim5.pcc.dto.BankDto;
import tim5.pcc.dto.mapper.BankMapper;
import tim5.pcc.model.Bank;
import tim5.pcc.service.template.BankService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value= "/bank", produces = MediaType.APPLICATION_JSON_VALUE)
public class BankController {
    @Autowired
    private BankService bankService;
    final private BankMapper mapper = new BankMapper();

    @GetMapping
    public List<BankDto> getAccounts() {
        List<BankDto> dtoList = new ArrayList<>();
        for (Bank bank : bankService.getAll())
            dtoList.add(mapper.BankToDto(bank));
        return dtoList;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BankDto> getBankById(@PathVariable Long id) {
        Bank bank = bankService.getById(id);
        if (bank != null) {
            return new ResponseEntity<>(mapper.BankToDto(bank), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<String> saveBank(@RequestBody BankDto dto) {
        if (isNullOrEmpty(dto.getPanNumber(), dto.getName(), dto.getUrl()))
            return new ResponseEntity<>("None of fields cannot be empty!", HttpStatus.BAD_REQUEST);
        Bank bank = mapper.DtoToBank(dto);
        return new ResponseEntity<>("Added bank with id " + bankService.create(bank).getId(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBank(@PathVariable Long id){
        Bank bank = bankService.delete(id);
        if(bank == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>("Bank deleted with id " + id, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BankDto> update(@PathVariable Long id, @RequestBody BankDto dto){
        if(!id.equals(dto.getId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Bank bank = mapper.DtoToBank(dto);
        Bank saved = bankService.update(bank);
        if (saved == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(mapper.BankToDto(saved),HttpStatus.OK);
    }
    private static boolean isNullOrEmpty (String...strArr){
        for (String st : strArr) {
            if (st == null || st.equals(""))
                return true;

        }
        return false;
    }
}

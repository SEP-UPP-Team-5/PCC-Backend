package tim5.pcc.dto.mapper;

import tim5.pcc.dto.BankDto;
import tim5.pcc.model.Bank;

public class BankMapper {
    public Bank DtoToBank(BankDto dto){
        return new Bank(dto.getId(), dto.getName(), dto.getUrl(), dto.getIdDigits());
    }
    public BankDto BankToDto(Bank bank){
        return new BankDto(bank.getId(), bank.getName(), bank.getUrl(), bank.getIdDigits());
    }
}

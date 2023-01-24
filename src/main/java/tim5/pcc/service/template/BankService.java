package tim5.pcc.service.template;

import tim5.pcc.model.Bank;

import java.util.List;

public interface BankService {

    Bank create(Bank bank);
    Bank getById(Long id);
    List<Bank> getAll();
    Bank update(Bank bank);
    Bank delete(Long id);

}

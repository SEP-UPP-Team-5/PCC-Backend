package tim5.pcc.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tim5.pcc.model.Bank;
import tim5.pcc.repository.BankRepository;
import tim5.pcc.service.template.BankService;

import java.util.List;
import java.util.Optional;

@Service
public class BankServiceImpl implements BankService {

    @Autowired
    private BankRepository bankRepository;

    @Override
    public Bank create(Bank bank) {
        return bankRepository.save(bank);
    }

    @Override
    public Bank getById(Long id) {
        return bankRepository.findById(id).orElse(null);
    }

    @Override
    public Bank getByIdDigits(String idDigits) {
        return bankRepository.getByIdDigits(idDigits);
    }

    @Override
    public List<Bank> getAll() {
        return bankRepository.findAll();
    }

    @Override
    public Bank update(Bank bank) {
        Bank account = bankRepository.findById(bank.getId()).get();
        if(account == null)
            return null;
        return bankRepository.save(bank);
    }

    @Override
    public Bank delete(Long id) {
        Optional<Bank> bank = bankRepository.findById(id);
        if(!bank.isPresent())
            return null;
        bankRepository.deleteById(id);
        return bank.get();
    }
}

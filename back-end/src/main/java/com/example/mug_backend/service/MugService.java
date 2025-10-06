package com.example.mug_backend.service;
import com.example.mug_backend.repository.MugRepository;
import com.example.mug_backend.model.Mug;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MugService {
    private final MugRepository _mugRepository;

    public MugService(MugRepository repository)
    {
        this._mugRepository = repository;
    }

    public List<Mug> findAll(){
        return _mugRepository.findAll();
    }

    public Mug findById(Long id){
        return _mugRepository.findById(id).orElse(null);
    }

    public Mug save(Mug mug){
        return _mugRepository.save(mug);
    }

    public Mug update(Long id, Mug mug){
        if(_mugRepository.existsById(id)){
            mug.setId(id);
            return _mugRepository.save(mug);
        }
        return null;
    }

    public boolean delete(Long id){
        if(_mugRepository.existsById(id)){
            _mugRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Mug> findByUserId(String userId) {
            return _mugRepository.findByUserId(userId);
        }
}

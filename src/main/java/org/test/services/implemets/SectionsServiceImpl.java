package org.test.services.implemets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.test.domain.entity.SectionsEntity;
import org.test.domain.repository.SectionsRepository;
import org.test.models.SectionsDto;
import org.test.services.interfaces.SectionsService;

import java.util.ArrayList;
import java.util.List;

@Service
public class SectionsServiceImpl implements SectionsService {
    private final SectionsRepository sectionsRepository;

    @Autowired
    public SectionsServiceImpl(SectionsRepository sectionsRepository){
        this.sectionsRepository = sectionsRepository;
    }

    public List<SectionsDto> getAll(){
       List<SectionsEntity> sectionsEntities = sectionsRepository.findAll();
        if( sectionsEntities != null && !sectionsEntities.isEmpty() && sectionsEntities.size() > 0){
            List<SectionsDto> sections = new ArrayList<>();
            sectionsEntities.forEach(section ->{
                SectionsDto sectionDto = converterSections(section);
                sections.add(sectionDto);
            });
            return sections;
        }
        return null;
    }

    private SectionsDto converterSections(SectionsEntity sectionsEntity){
        SectionsDto sectionsDto = new SectionsDto();
        sectionsDto.setId(sectionsEntity.getId().toString());
        sectionsDto.setSectionName(sectionsEntity.getSectionName());
        return sectionsDto;
    }
}

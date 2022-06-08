package com.example.pagingproject.Controller;

import com.example.pagingproject.Entity.PagingEntity;
import com.example.pagingproject.Impl.IPagingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PagingController {

    @Autowired
    IPagingRepo iPaging;

    //FAKE SAVE
    //http://localhost:8888/save
    @GetMapping("save")
    public String getSave(Model model, String pagingEntity){
        for (int i = 0; i <= 100; i++) {
            PagingEntity entity = PagingEntity.builder()
                    .id(0L)
                    .name("name"+i)
                    .surname("surname"+i)
                    .build();
            iPaging.save(entity);
            model.addAttribute("data",entity);
        }
        return "table";
    }

    //LIST
    //http://localhost:8888/list
    @GetMapping("list")
    public String listDatas(Model model){
        Iterable<PagingEntity> list = iPaging.findAll();
        model.addAttribute("list",list);
        return "table";
    }

    //PAGING

    //http://localhost:8888/paging/5/10
    @GetMapping("paging/{page}/{size}")
    public String pagingDatas(Model model,
                              @PathVariable(name = "page") int page,
                              @PathVariable(name = "size") int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<PagingEntity> list = iPaging.findAll(pageable);
        for (PagingEntity temp : list){
            model.addAttribute("list",list);
        }
        return "table";
    }


}

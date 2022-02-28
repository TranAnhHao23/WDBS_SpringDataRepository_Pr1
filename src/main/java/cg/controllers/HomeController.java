package cg.controllers;

import cg.model.Category;
import cg.model.Product;
import cg.service.ICategoryService;
import cg.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private IProductService productService;

    @Autowired
    private ICategoryService categoryService;

    @Value("${file-upload}")
    private String fileUpload;

    @Value("${view}")
    private String view;

//    @ModelAttribute

    @GetMapping
    public ModelAndView getAll(@PageableDefault(value = 5) Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("index");
        Page<Product> products = productService.findAll(pageable);
        if (products.isEmpty()) {
            modelAndView.addObject("message", "No products !!!");
        }
        modelAndView.addObject("view", view);

        modelAndView.addObject("products", products);
        return modelAndView;
    }

    @GetMapping("/createProduct")
    public ModelAndView createProduct() {
        ModelAndView modelAndView = new ModelAndView("create");
        Iterable<Category> categories = categoryService.findAll();
        modelAndView.addObject("categories", categories);
        modelAndView.addObject("product", new Product());
        return modelAndView;
    }

    @PostMapping("/save")
    public ModelAndView saveProduct(@ModelAttribute Product product) {
        ModelAndView modelAndView = new ModelAndView("create");
        MultipartFile multipartFile = product.getImageFile();
        String fileName = multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(product.getImageFile().getBytes(), new File(fileUpload + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        product.setImageLink(fileName);
        Product product1 = productService.save(product);
        if (product1 != null) {
            Iterable<Category> categories = categoryService.findAll();
            modelAndView.addObject("categories", categories);
            modelAndView.addObject("message", "Create Product Successfully !!!");
        }
        return modelAndView;
    }

    @GetMapping("/view")
    public ModelAndView viewDetail(@RequestParam("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("detail");
        Product product = productService.findById(id);
        modelAndView.addObject("view", view);
        modelAndView.addObject("product", product);
        return modelAndView;
    }

    @GetMapping("/delete")
    public ModelAndView delete(@RequestParam("id") Long id, @PageableDefault(value = 5) Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("index");
        productService.deleteById(id);
        Page<Product> products = productService.findAll(pageable);
        if (products.isEmpty()) {
            modelAndView.addObject("message", "No products !!!");
        }
        modelAndView.addObject("view", view);
        modelAndView.addObject("products", products);
        return modelAndView;
    }

    @GetMapping("/edit")
    public ModelAndView editProduct(@RequestParam("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("edit");
        Product product = productService.findById(id);
        modelAndView.addObject("product", product);
        modelAndView.addObject("view", view);
        modelAndView.addObject("categories", categoryService.findAll());
        return modelAndView;
    }

    @PostMapping("/update")
    public ModelAndView viewDetail12(@ModelAttribute Product product) {
        ModelAndView modelAndView = new ModelAndView("detail");
        modelAndView.addObject("view", view);
        modelAndView.addObject("product", product);
        return modelAndView;
    }

//    @PostMapping("/update")
//    public ModelAndView updateProduct(@ModelAttribute Product product) {
////        ModelAndView modelAndView = new ModelAndView("edit");
//////        if (product.getImageFile().getOriginalFilename().equals("")) {
//////            product.setImageLink(productService.findById(id).getImageLink());
////////        product.setImageFile(productService.findById(id).getImageFile());
//////        } else {
////            MultipartFile multipartFile = product.getImageFile();
////            String fileName = multipartFile.getOriginalFilename();
////            try {
////                FileCopyUtils.copy(product.getImageFile().getBytes(), new File(fileUpload + fileName));
////            } catch (IOException e) {
////                e.printStackTrace();
////            }
////            product.setImageLink(fileName);
//////        }
////        productService.save(product);
////        Iterable<Category> categories = categoryService.findAll();
////        modelAndView.addObject("categories", categories);
////        modelAndView.addObject("message", "Update Product Successfully !!!");
////        return modelAndView;
//    }

    @PostMapping("/searchByName")
    public ModelAndView searchByName(@RequestParam("searchByName") String name, @PageableDefault(value = 5) Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("index");
        Page<Product> products = productService.getAllProductByName(name, pageable);
        if (products.isEmpty()) {
            modelAndView.addObject("message", "No products !!!");
        }
        modelAndView.addObject("view", view);
        modelAndView.addObject("searchByName", name);
        modelAndView.addObject("products", products);
        return modelAndView;
    }


}

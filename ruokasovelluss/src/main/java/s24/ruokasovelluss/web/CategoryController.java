package s24.ruokasovelluss.web;

import java.util.List;
import s24.ruokasovelluss.domain.Category;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PostMapping;

import s24.ruokasovelluss.domain.CategoryR;

@Controller
public class CategoryController {
@Autowired
    private CategoryR categoryR;

    @GetMapping("/categorylist")
    public String getListOfCategories(Model model) {
        List<Category> listOfCategories = (List<Category>) categoryR.findAll();
        model.addAttribute("categories", listOfCategories);
        return "categorylist";
    };

    @GetMapping("/category/{id}")
    public String getMethodName(@PathVariable("id") Long id, Model model) {
        Optional<Category> optionalCategory = categoryR.findById(id);
        if (!optionalCategory.isPresent()) {
            throw new Error("Category not found");
        }
        Category category = optionalCategory.get();
        model.addAttribute("categories", category);
        return "categorylist";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/addcategory")
    public String getAddCategoryForm(Model model) {
        model.addAttribute("category", new Category());
        return "addcategoryform";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/savecategory")
    public String postCategory(Category category) {
        categoryR.save(category);
        return "redirect:/categorylist";
    }

    @GetMapping("/deletecategory/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getDeleteCategory(@PathVariable Long id) {
        categoryR.deleteById(id);
        return "redirect:/categorylist";
    }

    @GetMapping("/editcategory/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getEditCategory(@PathVariable Long id, Model model) {
        Optional<Category> optionalCategory = categoryR.findById(id);
        if (!optionalCategory.isPresent()) {
            throw new Error("Category not found");
        }
        Category category = optionalCategory.get();
        model.addAttribute("category", category);
        return "editcategoryform";
    }
}
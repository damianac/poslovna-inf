package agentapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import agentapp.domain.Category;
import agentapp.repository.CategoryRepository;
import agentapp.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Category addCategory(Category category) {
		return categoryRepository.save(category);
	}

	@Override
	public List<Category> getAllCategories() {
		return categoryRepository.findAll();
	}

}

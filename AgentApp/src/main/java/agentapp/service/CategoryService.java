package agentapp.service;

import java.util.List;

import agentapp.domain.Category;

public interface CategoryService {
	
	Category addCategory(Category category);
	
	List<Category> getAllCategories();
	
}

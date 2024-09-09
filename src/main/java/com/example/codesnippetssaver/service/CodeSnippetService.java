package com.example.codesnippetssaver.service;

import com.example.codesnippetssaver.model.CodeSnippet;
import com.example.codesnippetssaver.model.User;
import com.example.codesnippetssaver.repository.CodeSnippetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CodeSnippetService {

    @Autowired
    private CodeSnippetRepository repository;

    @Autowired
    private UserService userService;

    // Get all snippets for a specific user
    public List<CodeSnippet> getCodeSnippetsByUserId(Long userId) {
        return repository.findAllByUserId(userId);
    }

    // Get a specific snippet by ID and user
    public Optional<CodeSnippet> getCodeSnippetByIdAndUserId(Long id, Long userId) {
        return repository.findByIdAndUserId(id, userId);
    }

    public CodeSnippet save(CodeSnippet codeSnippet, Long userId) {
        User user = userService.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        codeSnippet.setUser(user);
        return repository.save(codeSnippet);
    }

    public CodeSnippet update(Long id, CodeSnippet codeSnippet, Long userId) {
        CodeSnippet existingSnippet = getCodeSnippetByIdAndUserId(id, userId)
                .orElseThrow(() -> new RuntimeException("Snippet not found or you do not have permission"));
        existingSnippet.setTitle(codeSnippet.getTitle());
        existingSnippet.setDescription(codeSnippet.getDescription());
        existingSnippet.setCode(codeSnippet.getCode());
        existingSnippet.setLanguages(codeSnippet.getLanguages());
        return repository.save(existingSnippet);
    }

    public void deleteById(Long id, Long userId) {
        CodeSnippet snippet = getCodeSnippetByIdAndUserId(id, userId)
                .orElseThrow(() -> new RuntimeException("Snippet not found or you do not have permission"));
        repository.delete(snippet);
    }
}
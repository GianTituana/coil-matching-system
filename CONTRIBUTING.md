# Contributing Guidelines

Thank you for your interest in contributing to the COIL Matching System! This document provides guidelines and instructions for contributing to the project.

## Code of Conduct

By participating in this project, you agree to abide by our code of conduct:

- Be respectful and inclusive
- Welcome newcomers and help them get started
- Focus on constructive feedback
- Respect different viewpoints and experiences
- Show empathy towards other community members

## How to Contribute

### Reporting Issues

#### Bug Reports
Before submitting a bug report:
1. Check existing issues to avoid duplicates
2. Use the latest version of the application
3. Provide clear steps to reproduce the issue

**Bug Report Template:**
```
**Bug Description**
A clear description of the bug.

**Steps to Reproduce**
1. Go to '...'
2. Click on '...'
3. Scroll down to '...'
4. See error

**Expected Behavior**
What you expected to happen.

**Actual Behavior**
What actually happened.

**Environment**
- OS: [e.g., Windows 10, macOS 12.0]
- Java Version: [e.g., 17.0.1]
- Spring Boot Version: [e.g., 3.2.0]
- Browser: [e.g., Chrome 91.0] (if applicable)

**Additional Context**
Add any other context about the problem here.
```

#### Feature Requests
Before submitting a feature request:
1. Check if the feature already exists
2. Review existing feature requests
3. Consider if it fits the project scope

**Feature Request Template:**
```
**Feature Summary**
Brief description of the feature.

**Motivation**
Why is this feature needed? What problem does it solve?

**Detailed Description**
Detailed description of the proposed feature.

**Acceptance Criteria**
- [ ] Criterion 1
- [ ] Criterion 2
- [ ] Criterion 3

**Additional Context**
Any additional information or context.
```

### Development Workflow

#### 1. Fork and Clone
```bash
# Fork the repository on GitHub
# Clone your fork
git clone https://github.com/YOUR_USERNAME/coil-matching-system.git
cd coil-matching-system

# Add upstream remote
git remote add upstream https://github.com/GianTituana/coil-matching-system.git
```

#### 2. Create a Branch
```bash
# Create and switch to a new branch
git checkout -b feature/your-feature-name

# Or for bug fixes
git checkout -b bugfix/issue-description
```

**Branch Naming Convention:**
- `feature/feature-name` - New features
- `bugfix/bug-description` - Bug fixes
- `hotfix/critical-fix` - Critical production fixes
- `docs/documentation-update` - Documentation changes
- `refactor/code-improvement` - Code refactoring

#### 3. Make Changes

##### Code Style Guidelines

**Java Code Style:**
- Use 4 spaces for indentation (no tabs)
- Follow Oracle Java naming conventions
- Maximum line length: 120 characters
- Use meaningful variable and method names
- Add JavaDoc comments for public methods

**Example:**
```java
/**
 * Finds courses matching the specified language and theme.
 *
 * @param idioma the language of instruction
 * @param tematica the subject theme
 * @return list of matching courses
 * @throws IllegalArgumentException if parameters are null or empty
 */
public List<Curso> realizarMatching(String idioma, String tematica) {
    if (idioma == null || idioma.trim().isEmpty()) {
        throw new IllegalArgumentException("Language cannot be null or empty");
    }
    if (tematica == null || tematica.trim().isEmpty()) {
        throw new IllegalArgumentException("Theme cannot be null or empty");
    }
    
    return cursoRepository.findByIdiomaAndTematica(idioma, tematica);
}
```

**Package Structure:**
```
com.ejemplo.coilmatching
├── controller/     # REST controllers
├── service/        # Business logic
├── repository/     # Data access
├── model/          # JPA entities
├── dto/            # Data transfer objects (planned)
├── exception/      # Custom exceptions (planned)
└── config/         # Configuration classes (planned)
```

##### Commit Message Guidelines

Follow conventional commits format:
```
<type>[optional scope]: <description>

[optional body]

[optional footer(s)]
```

**Types:**
- `feat`: New feature
- `fix`: Bug fix
- `docs`: Documentation changes
- `style`: Code style changes (formatting, etc.)
- `refactor`: Code refactoring
- `test`: Adding or updating tests
- `chore`: Maintenance tasks

**Examples:**
```bash
feat(matching): add advanced course matching algorithm
fix(api): resolve null pointer exception in course controller
docs(readme): update installation instructions
test(service): add unit tests for SistemaCOILService
```

#### 4. Testing Requirements

##### Unit Tests
- Write unit tests for all new functionality
- Maintain minimum 80% code coverage
- Use JUnit 5 and Mockito
- Follow AAA pattern (Arrange, Act, Assert)

**Example Test:**
```java
@ExtendWith(MockitoExtension.class)
class SistemaCOILServiceTest {

    @Mock
    private CursoRepository cursoRepository;

    @InjectMocks
    private SistemaCOILService service;

    @Test
    @DisplayName("Should return matching courses when valid criteria provided")
    void shouldReturnMatchingCourses() {
        // Arrange
        String idioma = "English";
        String tematica = "Computer Science";
        List<Curso> expectedCourses = Arrays.asList(
            new Curso("CS101", "Programming", idioma, tematica),
            new Curso("CS102", "Algorithms", idioma, tematica)
        );
        
        when(cursoRepository.findByIdiomaAndTematica(idioma, tematica))
            .thenReturn(expectedCourses);

        // Act
        Curso searchCourse = new Curso("", "", idioma, tematica);
        List<Curso> result = service.realizarMatching(searchCourse);

        // Assert
        assertThat(result).hasSize(2);
        assertThat(result).containsExactlyElementsOf(expectedCourses);
        verify(cursoRepository).findByIdiomaAndTematica(idioma, tematica);
    }
}
```

##### Integration Tests
- Test API endpoints with `@SpringBootTest`
- Test repository methods with `@DataJpaTest`
- Use TestContainers for database testing (if applicable)

#### 5. Documentation Updates

Update documentation when:
- Adding new features
- Changing API endpoints
- Modifying configuration
- Updating dependencies

Required documentation updates:
- Update README.md if needed
- Update API.md for new endpoints
- Add JavaDoc comments
- Update CHANGELOG.md

### Code Review Process

#### Before Submitting PR

**Self-Review Checklist:**
- [ ] Code follows style guidelines
- [ ] All tests pass locally
- [ ] New functionality has tests
- [ ] Documentation is updated
- [ ] No debugging code or console.log statements
- [ ] No commented-out code
- [ ] Commit messages follow convention
- [ ] Branch is up to date with main

#### Pull Request Guidelines

**PR Title Format:**
```
<type>: Brief description of changes
```

**PR Description Template:**
```
## Description
Brief description of what this PR does.

## Type of Change
- [ ] Bug fix (non-breaking change which fixes an issue)
- [ ] New feature (non-breaking change which adds functionality)
- [ ] Breaking change (fix or feature that would cause existing functionality to not work as expected)
- [ ] Documentation update

## Testing
- [ ] Unit tests added/updated
- [ ] Integration tests added/updated
- [ ] Manual testing completed

## Checklist
- [ ] Code follows style guidelines
- [ ] Self-review completed
- [ ] Documentation updated
- [ ] Tests added and passing

## Related Issues
Closes #123
```

#### Review Process

1. **Automated Checks** (when implemented)
   - Build status
   - Test coverage
   - Code quality checks

2. **Manual Review**
   - At least one approved review required
   - Focus on logic, design, and maintainability
   - Check for security vulnerabilities

3. **Merge Requirements**
   - All checks passing
   - Approved review
   - Up-to-date with main branch

### Getting Help

#### Communication Channels
- **GitHub Issues**: For bug reports and feature requests
- **GitHub Discussions**: For questions and general discussion
- **Email**: For private inquiries

#### Resources
- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Java Coding Standards](https://www.oracle.com/java/technologies/javase/codeconventions-contents.html)
- [Git Best Practices](https://git-scm.com/book/en/v2)

### Recognition

Contributors will be recognized in:
- CONTRIBUTORS.md file
- Release notes for significant contributions
- Annual contributor highlights

### Development Environment Setup

Refer to [DEVELOPMENT.md](DEVELOPMENT.md) for detailed setup instructions.

### Release Process

1. **Version Numbering**
   - Follow Semantic Versioning (SemVer)
   - Format: MAJOR.MINOR.PATCH

2. **Release Cycle**
   - Major releases: Breaking changes
   - Minor releases: New features
   - Patch releases: Bug fixes

3. **Release Checklist**
   - [ ] All tests passing
   - [ ] Documentation updated
   - [ ] CHANGELOG.md updated
   - [ ] Version numbers updated
   - [ ] Release notes prepared

### Common Contribution Scenarios

#### Adding a New API Endpoint

1. **Controller**: Add endpoint method
2. **Service**: Implement business logic
3. **Repository**: Add data access method (if needed)
4. **Tests**: Unit and integration tests
5. **Documentation**: Update API.md

#### Adding a New Entity

1. **Model**: Create JPA entity
2. **Repository**: Create repository interface
3. **Service**: Add business logic methods
4. **Controller**: Add CRUD endpoints
5. **Tests**: Complete test coverage
6. **Documentation**: Update schema documentation

#### Bug Fix

1. **Reproduce**: Create test that reproduces the bug
2. **Fix**: Implement the fix
3. **Verify**: Ensure test passes
4. **Regression**: Run full test suite
5. **Document**: Update relevant documentation

### Security Guidelines

- Never commit sensitive information (passwords, API keys)
- Use environment variables for configuration
- Follow OWASP security guidelines
- Report security vulnerabilities privately

### Performance Guidelines

- Write efficient database queries
- Use appropriate data structures
- Consider memory usage
- Profile code for bottlenecks
- Use caching where appropriate

Thank you for contributing to the COIL Matching System!
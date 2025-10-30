# SonarQube/SonarCloud Setup Instructions

## Prerequisites
1. Create a SonarCloud account at https://sonarcloud.io
2. Create a new project in SonarCloud
3. Get your SonarCloud token

## GitHub Secrets Configuration

Add the following secrets to your GitHub repository (Settings → Secrets and variables → Actions):

### Required Secrets:
1. **SONAR_TOKEN**: Your SonarCloud authentication token
   - Get it from: SonarCloud → My Account → Security → Generate Token

2. **SONAR_PROJECT_KEY**: Your project key from SonarCloud
   - Format: `your-org_project-name`
   - Found in: SonarCloud → Project → Information

3. **SONAR_ORGANIZATION**: Your SonarCloud organization key
   - Found in: SonarCloud → My Organizations

## Steps to Set Up:

### 1. Create SonarCloud Project
- Go to https://sonarcloud.io
- Click "+" → "Analyze new project"
- Import your GitHub repository
- Copy the **Project Key** and **Organization Key**

### 2. Generate Authentication Token
- Go to: My Account → Security → Generate Tokens
- Name: `GitHub Actions`
- Type: User Token
- Copy the generated token

### 3. Add GitHub Secrets
```bash
# Go to your GitHub repository
Settings → Secrets and variables → Actions → New repository secret

Add these secrets:
- SONAR_TOKEN: (paste your token)
- SONAR_PROJECT_KEY: (paste your project key)
- SONAR_ORGANIZATION: (paste your organization key)
```

### 4. Update Configuration Files
Edit `Spring-Boot-Backend/pom.xml`:
```xml
<properties>
    <sonar.organization>YOUR_ORGANIZATION_KEY</sonar.organization>
</properties>
```

Edit `Spring-Boot-Backend/sonar-project.properties`:
```properties
sonar.projectKey=YOUR_PROJECT_KEY
sonar.organization=YOUR_ORGANIZATION_KEY
```

### 5. Push Changes
```bash
git add .
git commit -m "Add SonarQube/SonarCloud integration"
git push origin main
```

## What Gets Analyzed?

The SonarCloud scan will analyze:
- ✅ Code quality
- ✅ Code smells
- ✅ Bugs
- ✅ Security vulnerabilities
- ✅ Code coverage (via JaCoCo)
- ✅ Duplications
- ✅ Technical debt

## View Results

After the CI/CD pipeline runs:
1. Go to https://sonarcloud.io
2. Navigate to your project
3. View the dashboard with quality metrics

## Quality Gates

SonarCloud will fail the build if:
- New bugs are introduced
- New vulnerabilities are found
- Code coverage drops below threshold
- Code smells exceed limits

You can customize these rules in SonarCloud → Quality Gates.

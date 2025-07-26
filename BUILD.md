# 🛠️ **BUILD GUIDE - ALGORITHMIC PATTERNS LIBRARY**

## 📋 **Prerequisites**

### **☕ Java Requirements**
- **Minimum**: JDK 11+
- **Recommended**: JDK 21 (Latest LTS)
- **Check version**: `java -version && javac -version`

### **📦 Build Tools**
- **Maven**: 3.6+ (Primary build tool)
- **Git**: Latest version for version control

### **💻 IDE Support**
- ✅ **IntelliJ IDEA** (Recommended)
- ✅ **Eclipse IDE**
- ✅ **Visual Studio Code** with Java extensions
- ✅ **Command Line** builds

---

## ⚡ **Quick Build**

### **🚀 One-Command Build**
```bash
# Clone, build, and verify
git clone <repository-url> && cd DsAlgoProject && mvn compile && ./clean.sh
```

### **📋 Step-by-Step Setup**
```bash
# 1. Clone the repository
git clone <repository-url>
cd DsAlgoProject

# 2. Verify Java version
java -version
javac -version

# 3. Compile project
mvn compile

# 4. Clean build artifacts (optional)
./clean.sh

# 5. Verify everything works
mvn clean compile
```

---

## 🔧 **Build Commands Reference**

### **📊 Maven Commands**
```bash
# Basic compilation
mvn compile                     # Compile all source files
mvn clean compile              # Clean and compile
mvn package                    # Create JAR file
mvn clean package             # Clean and package

# Development commands
mvn clean                      # Remove target directory
mvn test                       # Run tests (when available)
mvn install                    # Install to local repository
```

### **⚡ Direct Java Compilation**
```bash
# Compile all files
javac -cp src src/com/dsalgo/AlgoPatterns/*/*.java

# Compile specific category
javac -cp src src/com/dsalgo/AlgoPatterns/Arrays/*.java

# Compile single file
javac -cp src src/com/dsalgo/AlgoPatterns/Arrays/TwoPointers.java
```

### **🧹 Cleanup Commands**
```bash
# Complete cleanup (recommended)
./clean.sh

# Manual cleanup
rm -rf target/                  # Remove Maven build directory
find src -name "*.class" -delete  # Remove .class files from source
```

---

## 🏗️ **Build Verification**

### **✅ Build Success Checklist**
```bash
# 1. Compilation check
mvn compile
# Expected: BUILD SUCCESS

# 2. Package creation
mvn package
# Expected: JAR file in target/

# 3. Direct compilation
javac -cp src src/com/dsalgo/AlgoPatterns/*/*.java
# Expected: No errors

# 4. Execution test
cd target/classes
java com.dsalgo.AlgoPatterns.Arrays.TwoPointers
# Expected: Test output displayed
```

### **📊 Expected Output**
```
[INFO] BUILD SUCCESS
[INFO] Total time: 3-5 seconds
[INFO] Compiling 87 source files
```

---

## 🎯 **IDE Setup**

### **🧠 IntelliJ IDEA**
1. **Import Project**: File → Open → Select `DsAlgoProject` folder
2. **Configure JDK**: File → Project Structure → Project SDK → Select JDK 11+
3. **Maven Integration**: Maven panel should auto-detect `pom.xml`
4. **Build**: Build → Build Project or `Ctrl+F9`

### **🌙 Eclipse IDE**
1. **Import**: File → Import → Existing Maven Projects
2. **Select**: Browse to `DsAlgoProject` folder
3. **Configure**: Right-click project → Properties → Java Build Path
4. **Build**: Project → Build All or `Ctrl+B`

### **📝 VS Code**
1. **Open Folder**: File → Open Folder → Select `DsAlgoProject`
2. **Extensions**: Install Java Extension Pack
3. **Configure**: Press `F1` → "Java: Configure Runtime"
4. **Build**: Press `F1` → "Java: Compile Workspace"

---

## 🐛 **Troubleshooting**

### **❌ Common Build Issues**

#### **Problem**: "enum types must not be local"
```bash
# Solution: Fixed in v2.0 - update to latest version
git pull origin main
```

#### **Problem**: Cannot find symbol errors
```bash
# Solution: Clean and rebuild
mvn clean compile
```

#### **Problem**: Maven not found
```bash
# Solution: Install Maven
# macOS: brew install maven
# Ubuntu: sudo apt install maven
# Windows: Download from maven.apache.org
```

#### **Problem**: Wrong Java version
```bash
# Check current version
java -version

# Set JAVA_HOME (example for macOS)
export JAVA_HOME=$(/usr/libexec/java_home -v 11)

# Verify
echo $JAVA_HOME
```

### **🔍 Debug Information**
```bash
# Get detailed build info
mvn compile -X                  # Verbose Maven output
mvn help:system                # System information
./clean.sh                     # Project status after cleanup
```

---

## 📈 **Performance Optimization**

### **⚡ Faster Builds**
```bash
# Parallel compilation
mvn -T 4 compile               # Use 4 threads

# Skip tests (when available)
mvn compile -DskipTests

# Offline mode (use cached dependencies)
mvn -o compile
```

### **💾 Memory Optimization**
```bash
# Increase Maven memory
export MAVEN_OPTS="-Xmx2g -XX:MaxPermSize=256m"

# For large projects
mvn compile -Dmaven.compiler.fork=true
```

---

## 🚀 **Production Build**

### **📦 Creating Distribution**
```bash
# Complete production build
mvn clean package

# Create standalone JAR
mvn clean compile assembly:single

# Prepare for deployment
./clean.sh                     # Clean repository
mvn clean package             # Build fresh
```

### **✅ Pre-Release Checklist**
- [ ] All tests pass: `mvn test`
- [ ] Clean compilation: `mvn clean compile`
- [ ] No .class files in repo: `./clean.sh`
- [ ] Documentation updated
- [ ] CHANGELOG.md updated
- [ ] Version tagged in Git

---

## 🎓 **Best Practices**

### **🔄 Development Workflow**
```bash
# 1. Start development
git pull origin main           # Get latest changes
mvn clean compile             # Verify build

# 2. During development
mvn compile                   # Quick compilation check
# Make your changes...

# 3. Before committing
./clean.sh                    # Clean build artifacts
mvn clean compile             # Final verification
git add .                     # Stage changes
git commit -m "Description"   # Commit with clear message
```

### **🧹 Repository Hygiene**
- ✅ **Always run** `./clean.sh` before Git commits
- ✅ **Never commit** `.class` files or `target/` directory
- ✅ **Use** `mvn clean` for fresh builds
- ✅ **Verify** build success before pushing

### **⚡ Quick Commands**
```bash
# Daily development
alias build='mvn compile'
alias clean-build='mvn clean compile' 
alias full-clean='./clean.sh && mvn clean compile'

# Add to your ~/.bashrc or ~/.zshrc for convenience
```

---

## 🆘 **Getting Help**

### **📚 Resources**
- **Maven Documentation**: https://maven.apache.org/guides/
- **Java Documentation**: https://docs.oracle.com/en/java/
- **IDE Setup Guides**: Check IDE-specific documentation

### **🐛 Support**
- **Issues**: Open GitHub issue with build logs
- **Questions**: Check existing issues and discussions
- **Contributions**: Fork, fix, and submit pull request

---

**Build Status**: ✅ **100% Success** | **Last Updated**: July 2024 | **Tested On**: Java 11, 17, 21 
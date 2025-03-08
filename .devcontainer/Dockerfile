# Use Ubuntu as the base image
FROM ubuntu:22.04

# Set non-interactive mode to prevent prompts during installation
ENV DEBIAN_FRONTEND=noninteractive

# Update and install required packages
RUN apt-get update && apt-get install -y \
    openjdk-17-jdk \        
    curl \                  
    git \                  
    unzip \                 
    bash \                  
    ca-certificates \       
    && apt-get clean \
    && rm -rf /var/lib/apt/lists/*

# Install Node.js 20.x
RUN curl -fsSL https://deb.nodesource.com/setup_20.x | bash - && \
    apt-get install -y nodejs

# Set default shell to bash
SHELL ["/bin/bash", "-c"]

# Set the working directory
WORKDIR /app

# Expose necessary ports (Spring Boot: 8080, React: 3000)
EXPOSE 8080 3000

# Default command (this can be overridden)
CMD ["bash"]

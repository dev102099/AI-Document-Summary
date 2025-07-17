# 🧠 AI-Powered Document Summarizer

A sleek, responsive web app that uses AI to generate concise summaries from uploaded documents (PDF, DOCX, TXT). Built using React for the frontend and Spring Boot 3.2+ (Java 21) for the backend. Summarization is powered by the Cohere API.

---

## 🛠️ Tech Stack

**Frontend:**

- React
- Tailwind CSS
- react-simple-typewriter

**Backend:**

- Java 21
- Spring Boot 3.2+
- Spring Web + Multipart support
- Cohere AI API
- RESTful APIs
- Apache Tika (Text extraction)

---

## ✨ Features

- 📄 Upload `.pdf`, `.doc`, `.docx`, or `.txt` files
- 🤖 AI-generated summaries with Cohere
- ⌨️ Animated output with typing effect
- ⏳ Loading spinner while processing
- ❌ Error handling for failed uploads or summaries
- 🎨 Beautiful, minimal UI with dark theme

---

## 📦 Installation

### Prerequisites

- Java 21 installed
- Maven (or use Spring Boot’s built-in wrapper)
- Node.js v18+
- A Cohere API key (https://cohere.com) [update the key at :
  Cohere cohere = Cohere.builder()
  .token(your api key)
  .build(); in service section.]

---

## 🚀 Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/your-username/ai-document-summarizer.git
cd ai-document-summarizer
```

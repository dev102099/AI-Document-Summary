import React, { useState } from "react";
import { Typewriter } from "react-simple-typewriter";
import Spinner from "./components/Spinner";
function App() {
  const [file, setFile] = useState(null);
  const [summary, setSummary] = useState("");
  const [loading, setLoading] = useState(false);
  const [write, setWrite] = useState(false);
  const [error, setError] = useState("");

  const submitSummary = async () => {
    try {
      if (!file) {
        setError("Please select a file first.");
        alert(error);
        return;
      }
      setLoading(true);

      const formData = new FormData();
      formData.append("file", file);
      console.log("FormData contents:", [...formData.entries()]);
      const res = await fetch("http://localhost:8080/summarize", {
        method: "POST",
        body: formData,
      });
      const text = await res.json();
      setSummary(text.Summary);
      if (res.ok) {
        setWrite(true);
        setLoading(false);
      } else {
        setLoading(false);
        setError("Failed to summarize the document.");
        alert(error);
      }
    } catch (error) {
      console.error("Error submitting summary:", error);
    }
  };

  const handleDone = () => {
    setLoading(false);

    setSummary("");

    setError("");
  };
  return (
    <>
      <div className="bg-black h-screen w-screen bg-[linear-gradient(136deg,_#434343,_#000000)] flex items-center justify-center text-white">
        <div class="backdrop-blur-md bg-white/5 p-8 rounded-2xl shadow-xl w-full max-w-md flex flex-col gap-4">
          <h1 class="text-xl font-semibold text-center">
            AI-Powered Document Summarizer
          </h1>

          <div className="flex gap-5 border-1 p-3 border-white/20 rounded-2xl">
            <input
              type="file"
              className="hidden"
              id="fileInput"
              onChange={(e) => {
                setFile(e.target.files[0]);
              }}
              accept=".pdf,.doc,.docx,.txt"
            />
            {console.log(file)}
            <label
              htmlFor="fileInput"
              className="text-white cursor-pointer font-semibold  font-roboto"
            >
              Choose file
            </label>
            <span className="text-white font-roboto">
              {file != null ? `${file.name}` : "No file selected"}
            </span>
          </div>

          <button
            onClick={submitSummary}
            disabled={loading}
            class={`bg-blue-500 hover:opacity-80 text-white p-2 rounded-lg transition`}
          >
            {loading ? <Spinner /> : "Summarize Document"}
          </button>

          {write ? (
            <div className="bg-white/10 p-2 rounded-lg border border-white/20 text-sm text-white placeholder-white/50 resize-none h-32 overflow-auto">
              <Typewriter
                key={summary}
                words={[summary]}
                loop={1}
                cursor
                cursorStyle="_"
                typeSpeed={10}
                onTypeDone={() => {
                  console.log("Typing finished");
                  handleDone();
                }}
              />
            </div>
          ) : (
            <div className="bg-white/10 p-2 rounded-lg border border-white/20 text-sm text-white placeholder-white/50 resize-none h-32 overflow-auto">
              Summary will appear here...
            </div>
          )}
        </div>
      </div>
    </>
  );
}

export default App;

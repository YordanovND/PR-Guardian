#!/bin/sh
ollama serve &
sleep 5
ollama pull qwen2.5-coder:3b
wait
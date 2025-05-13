package com.vti.shopee_project.response;


public class GeminiResponse {
    public static class Candidate {
        public Content content;
    }

    public static class Content {
        public Part[] parts;
    }

    public static class Part {
        public String text;
    }

    public Candidate[] candidates;
}


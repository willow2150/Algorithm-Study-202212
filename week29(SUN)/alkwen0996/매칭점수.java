import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 매칭점수 {
    // meta 태그의 content 속성이 해당 웹페이지의 url
    // a 태그의 href 속성이 외부링크
    // 검색어(word) 검색시 대소문자 무시,
    // 검색어는 단어단위로 비교, 완전히 일치하는 경우에만 기본점수에 반영 (알파벳을 제외한 다른 모든 문자로 구분)
    // 동일 매칭점수가 여러개이면 index 번호가 가장 작은것을 리턴.

    // 1. 각 페이지의 url을 찾는다.
    // 2. 각 페이지의 외부링크를 찾는다.
    // 3. 각 페이지마다 들어오는 링크를 찾는다.
    // 4. 단어의 개수를 찾는다.
    // 5. 각 페이지의 점수를 계산한다.

    public static void main(String[] args) {
        String word = "blind";
        String[] pages = {"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://a.com\"/>\n</head>  \n<body>\nBlind Lorem Blind ipsum dolor Blind test sit amet, consectetur adipiscing elit. \n<a href=\"https://b.com\"> Link to b </a>\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://b.com\"/>\n</head>  \n<body>\nSuspendisse potenti. Vivamus venenatis tellus non turpis bibendum, \n<a href=\"https://a.com\"> Link to a </a>\nblind sed congue urna varius. Suspendisse feugiat nisl ligula, quis malesuada felis hendrerit ut.\n<a href=\"https://c.com\"> Link to c </a>\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://c.com\"/>\n</head>  \n<body>\nUt condimentum urna at felis sodales rutrum. Sed dapibus cursus diam, non interdum nulla tempor nec. Phasellus rutrum enim at orci consectetu blind\n<a href=\"https://a.com\"> Link to a </a>\n</body>\n</html>"};

        System.out.println(solution(word, pages));
    }

    public static int solution(final String word, final String[] pages) {
        List<Page> pagesInfo = new ArrayList<>();
        Map<String, Double> linkScore = new HashMap<>();

        for (int i = 0; i < pages.length; i++) {
            String page = pages[i];
            int urlStartIndex = page.indexOf("content=") + 9;
            String url = page.substring(urlStartIndex).split("\"")[0];

            int bodyStartIndex = page.indexOf("<body>") + 7;
            int bodyEndIndex = page.indexOf("</body>");
            String[] splitBody = page.substring(bodyStartIndex, bodyEndIndex).split("\n");

            int sameWordCount = 0;
            String outUrl = "";
            List<String> outLinks = new ArrayList<>();

            for (int j = 0; j < splitBody.length; j++) {
                boolean isOutUrl = splitBody[j].startsWith("<a href=");

                if (isOutUrl) {
                    String temp = splitBody[j].split("\">")[0];
                    outUrl = temp.split("\"")[1];
                    outLinks.add(outUrl);
                } else {
                    String[] splitWords = splitBody[j].split("[^a-zA-Z]");

                    for (final String splitWord : splitWords) {
                        if (word.toLowerCase().equals(splitWord.trim().toLowerCase())) {
                            sameWordCount++;
                        }
                    }
                }
            }

            for (int j = 0; j < outLinks.size(); j++) {
                String outLink = outLinks.get(j);
                double likeScore = sameWordCount / (double) outLinks.size();

                if (linkScore.getOrDefault(outLink, (double) 0f) == 0.0) {
                    linkScore.put(outLink, (linkScore.getOrDefault(outLink, (double) 0) + likeScore));
                } else {
                    linkScore.put(outLink, linkScore.get(outLink) + likeScore);
                }
            }

            pagesInfo.add(new Page(i, url, outLinks, sameWordCount, 0));
        }

        for (Page page : pagesInfo) {
            page.totalScore = page.basicScore + linkScore.getOrDefault(page.url, (double) 0);
        }

        Collections.sort(pagesInfo);

        return pagesInfo.get(0).index;
    }

    static class Page implements Comparable<Page> {
        private int index;
        private String url;
        private List<String> outUrls;
        private int basicScore;
        private double totalScore;

        public Page(final int index, final String url, final List<String> outUrls, final int basicScore, final double totalScore) {
            this.index = index;
            this.url = url;
            this.outUrls = outUrls;
            this.basicScore = basicScore;
            this.totalScore = totalScore;
        }

        @Override
        public String toString() {
            return "Page{" +
                    "index=" + index +
                    ", url='" + url + '\'' +
                    ", outUrls=" + outUrls +
                    ", score=" + basicScore +
                    ", totalScore=" + totalScore +
                    '}';
        }

        @Override
        public int compareTo(final Page page) {
            if (this.totalScore == page.totalScore) {
                if (this.index > page.index) {
                    return -1;
                } else {
                    return 1;
                }
            } else if (this.totalScore > page.totalScore) {
                return -1;
            } else {
                return 1;
            }
        }
    }

}

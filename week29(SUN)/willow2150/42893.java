import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Solution {
    static class Page {
        List<Page> externalLinkPages;
        double primaryScore;
        double linkScore;
        int numOfExternalLinks;

        Page() {
            this.externalLinkPages = new ArrayList<>();
        }
    }

    final String LINK_PATTERN_STRING = "<meta property=\"og:url\" content=\"(https://\\S*)\"/>";
    final String EXTERNAL_LINK_PATTERN_STRING = "<a href=\"(https://\\S*)\">";
    final String BODY_PATTERN_STRING = "<body>|</body>";
    final Pattern LINK_PATTERN = Pattern.compile(LINK_PATTERN_STRING);
    final Pattern EXTERNAL_LINK_PATTERN = Pattern.compile(EXTERNAL_LINK_PATTERN_STRING);

    public int solution(String word, String[] pages) {
        Map<String, Page> pageMap = new HashMap<>();
        Page[] pageArray = new Page[pages.length];
        double maxMatchingScore = 0D;
        int maxMatchingScorePageIndex = 0;

        for (int pageIndex = 0; pageIndex < pages.length; pageIndex++) {
            Page page = new Page();
            pageArray[pageIndex] = page;

            Matcher linkMatcher = LINK_PATTERN.matcher(pages[pageIndex]);
            if (linkMatcher.find()) {
                String link = linkMatcher.group(1);
                pageMap.put(link, page);
            }

            String bodyContent = pages[pageIndex].split(BODY_PATTERN_STRING)[1];
            for (String text : bodyContent.split("[^a-zA-Z]+")) {
                if (text.equalsIgnoreCase(word)) {
                    page.primaryScore++;
                }
            }
        }

        for (int pageIndex = 0; pageIndex < pages.length; pageIndex++) {
            Page page = pageArray[pageIndex];

            Matcher externalLinkMatcher =
                    EXTERNAL_LINK_PATTERN
                            .matcher(pages[pageIndex]);
            while (externalLinkMatcher.find()) {
                String externalLink = externalLinkMatcher.group(1);
                Page externalPage = pageMap.get(externalLink);
                if (externalPage != null) {
                    page.externalLinkPages.add(externalPage);
                }
                page.numOfExternalLinks++;
            }

            double linkScore = page.primaryScore / page.numOfExternalLinks;
            for (Page externalLinkPage : page.externalLinkPages) {
                externalLinkPage.linkScore += linkScore;
            }
        }

        for (int pageIndex = 0; pageIndex < pages.length; pageIndex++) {
            Page page = pageArray[pageIndex];
            if (maxMatchingScore < page.primaryScore + page.linkScore) {
                maxMatchingScore = page.primaryScore + page.linkScore;
                maxMatchingScorePageIndex = pageIndex;
            }
        }

        return maxMatchingScorePageIndex;
    }
}

package com.fmyblack.textClassify.naiveBayes;

import java.util.*;

import com.fmyblack.textClassify.ClassifyModel;
import com.fmyblack.textClassify.IDF;
import com.fmyblack.textClassify.Result;
import com.fmyblack.textClassify.doc.Document;
import com.fmyblack.textClassify.doc.IDFDocIterable;
import com.fmyblack.textClassify.doc.TFDocIterable;
import com.fmyblack.word.WordSegmenter;

public class NaiveBayesClassifier implements ClassifyModel {

	List<Tag> tags = new ArrayList<Tag>();
	WordSegmenter ws;

	public NaiveBayesClassifier(WordSegmenter wordSegmenter) {
		ws = wordSegmenter;
	};

	public void train(Map<String, List<String>> trainSeeds) {

		System.out.println("start training ...");

		System.out.println("start loading document...");
		List<String> allDocs = new ArrayList<String>();
		for (Map.Entry<String, List<String>> entry : trainSeeds.entrySet()) {
			List<String> tagDocs = entry.getValue();
			allDocs.addAll(tagDocs);
			TFDocIterable tfDocs = new TFDocIterable(tagDocs, ws);
			tags.add(new Tag(entry.getKey(), tfDocs));
		}

		IDFDocIterable docs = new IDFDocIterable(allDocs, ws);
		System.out.println("start training idf...");
		IDF idf = new IDF(docs);

		System.out.println("start training tag...");
		for (Tag tag : tags) {
			tag.fit(idf);
		}
	}

	public Result classify(String path) {
		Document doc = new Document(path);
		Map<String, Integer> wordsFre = doc.docToWords(ws);
		System.out.println("------------------");
		System.out.println(wordsFre.toString());
		int wordsNum = 0;
		for (Map.Entry<String, Integer> entry : wordsFre.entrySet()) {
			wordsNum += entry.getValue();
		}
		Result currentResult = null;
		double maxAccuracy = -Double.MAX_VALUE;
		for (Tag tag : this.tags) {
			Result tmpResult = tag.caculateAccuracy(wordsFre);
			if (tmpResult.getAccuracy() > maxAccuracy) {
				currentResult = tmpResult;
				maxAccuracy = currentResult.getAccuracy();
			}
		}
		currentResult.setAccuracy(maxAccuracy / wordsNum * 100);
		return currentResult;
	}

}

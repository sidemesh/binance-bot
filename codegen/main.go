package main

import (
	bytes2 "bytes"
	"encoding/json"
	"fmt"
	"io/ioutil"
	"log"
	"net/http"
	"os"
	"text/template"
)

type ExchangeInfo struct {
	Code string   `json:"code"`
	Data []Symbol `json:"data"`
}

type Symbol struct {
	BaseAsset      string `json:"baseAsset"`
	QuoteAsset     string `json:"quoteAsset"`
	MinTradeAmount string `json:"minTradeAmount"`
	MinStepSize    string `json:"minStepSize"`
	MinTickSize    string `json:"minTickSize"`
}

func (s Symbol) ToJavaEnum() string {
	// TODO
	if s.BaseAsset == "1INCH" {
		return fmt.Sprintf("%s_%s(\"%s%s\", \"%s\", \"%s\"),",
			"$1INCH", s.QuoteAsset,
			s.BaseAsset, s.QuoteAsset,
			s.MinTickSize,
			s.MinTradeAmount)
	}
	return fmt.Sprintf("%s_%s(\"%s%s\", \"%s\", \"%s\"),",
		s.BaseAsset, s.QuoteAsset,
		s.BaseAsset, s.QuoteAsset,
		s.MinTickSize,
		s.MinTradeAmount)
}

func init() {
	err := os.Setenv("HTTPS_PROXY", "http://127.0.0.1:7890")
	if err != nil {
		panic(err)
	}
}

func main() {
	resp, err := http.Get(ExchangeInfoAPI)
	if err != nil {
		panic(err)
	}
	defer resp.Body.Close()

	bytes, err := ioutil.ReadAll(resp.Body)
	if err != nil {
		panic(err)
	}

	var exchangeInfo ExchangeInfo
	err = json.Unmarshal(bytes, &exchangeInfo)
	if err != nil {
		panic(err)
	}

	tplBytes, err := ioutil.ReadFile("./symbol.tpl")
	if err != nil {
		panic(err)
	}

	tpl, err := template.New("symbol.tpl").Parse(string(tplBytes))
	if err != nil {
		panic(tpl)
	}

	buf := bytes2.NewBuffer(make([]byte, 0))
	err = tpl.Execute(buf, exchangeInfo.Data)
	if err != nil {
		panic(err)
	}

	err = ioutil.WriteFile("../src/main/java/com/sidemesh/binance/bot/Symbol.java", buf.Bytes(), 0644)
	if err != nil {
		panic(err)
	}

	log.Println("finished!")
}
